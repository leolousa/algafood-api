package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
/**
 * Classe de implementação da validação customizada
 * Se a taxaFrete for 0 então o nome do restaurante
 * deve conter no seu nome o texto "Frete Grátis"
 * 
 * @author Leonardo
 *
 */
public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	
	
	@Override //Atribuindo os parâmetros às variáveis de instância da classe
	public void initialize(ValorZeroIncluiDescricao constraint) {
		this.valorField = constraint.valorField();
		this.descricaoField = constraint.descricaoField();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
		

	}
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		boolean valido = true;
		
		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
					.getReadMethod().invoke(objetoValidacao);
			
			String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
					.getReadMethod().invoke(objetoValidacao);
			
			if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
			
			return valido;
			
		} catch (Exception e) {
			throw new ValidationException(e); 
		}
				
	}

	
}

package com.karthik.constraints;

import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Author : Karthik Suresh <br>
 */
public class NoEmptyNullElementsValidator implements ConstraintValidator<NoEmptyNullElements, List<Long>> {

	@Override
	public void initialize(NoEmptyNullElements noNullElements) {
	}

	@Override
	public boolean isValid(List<Long> list, ConstraintValidatorContext context) {
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		return list.stream().allMatch(Objects::nonNull);
	}

}
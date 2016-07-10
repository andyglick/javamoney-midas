/*
 * Copyright (c) 2015, 2016, Werner Keil and others by the @author tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.midas.javaee7.validator;

import static java.util.Collections.binarySearch;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyRejectedValidator implements ConstraintValidator<CurrencyRejected, CurrencyUnit>{


	private final List<CurrencyUnit> currencies = new ArrayList<>();

	@Override
	public void initialize(CurrencyRejected constraintAnnotation) {
		CurrencyReaderConverter reader = new CurrencyReaderConverter(constraintAnnotation);
		currencies.addAll(reader.getCurrencies());
		sort(currencies);
	}

	@Override
	public boolean isValid(CurrencyUnit value,
			ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return !containsCurrency(value);
	}

	private boolean containsCurrency(CurrencyUnit value) {
		return binarySearch(currencies, value) >= 0;
	}

	List<CurrencyUnit> getCurrencies() {
		return currencies;
	}
}

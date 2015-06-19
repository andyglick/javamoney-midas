package org.javamoney.midas.javaee7.cdi;

import javax.enterprise.inject.Produces;
import javax.money.MonetaryOperator;

import org.javamoney.moneta.function.MonetaryOperators;

class MonetaryAmountProducer {

	@Produces
	public MonetaryOperator getRoundingDefault() {
		return MonetaryOperators.rounding();
	}
}

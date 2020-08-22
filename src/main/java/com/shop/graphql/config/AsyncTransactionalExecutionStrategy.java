package com.shop.graphql.config;

import graphql.ExecutionResult;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionContext;
import graphql.execution.ExecutionStrategyParameters;
import graphql.execution.NonNullableFieldWasNullException;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsyncTransactionalExecutionStrategy
	extends AsyncExecutionStrategy {

	@Override
	@Transactional
	public CompletableFuture<ExecutionResult> execute(
		ExecutionContext executionContext,
		ExecutionStrategyParameters parameters
	)
		throws NonNullableFieldWasNullException {
		return super.execute(executionContext, parameters);
	}
}

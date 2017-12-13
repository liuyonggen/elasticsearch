/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.sql.expression.function.scalar.arithmetic;

import org.elasticsearch.xpack.sql.expression.Expression;
import org.elasticsearch.xpack.sql.expression.function.scalar.arithmetic.BinaryArithmeticProcessor.BinaryArithmeticOperation;
import org.elasticsearch.xpack.sql.expression.function.scalar.processor.definition.BinaryProcessorDefinition;
import org.elasticsearch.xpack.sql.expression.function.scalar.processor.definition.ProcessorDefinition;

import java.util.Objects;

public class BinaryArithmeticProcessorDefinition extends BinaryProcessorDefinition {

    private final BinaryArithmeticOperation operation;

    public BinaryArithmeticProcessorDefinition(Expression expression, ProcessorDefinition left, ProcessorDefinition right, BinaryArithmeticOperation operation) {
        super(expression, left, right);
        this.operation = operation;
    }

    public BinaryArithmeticOperation operation() {
        return operation;
    }

    @Override
    public BinaryArithmeticProcessor asProcessor() {
        return new BinaryArithmeticProcessor(left().asProcessor(), right().asProcessor(), operation);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(left(), right(), operation);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        BinaryArithmeticProcessorDefinition other = (BinaryArithmeticProcessorDefinition) obj;
        return Objects.equals(operation, other.operation)
                && Objects.equals(left(), other.left())
                && Objects.equals(right(), other.right());
    }
}

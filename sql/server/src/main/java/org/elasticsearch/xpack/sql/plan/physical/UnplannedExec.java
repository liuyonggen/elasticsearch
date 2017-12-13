/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.sql.plan.physical;

import java.util.List;
import java.util.Objects;

import org.elasticsearch.xpack.sql.expression.Attribute;
import org.elasticsearch.xpack.sql.plan.logical.LogicalPlan;

public class UnplannedExec extends LeafExec implements Unexecutable {

    private final LogicalPlan plan;

    public UnplannedExec(LogicalPlan plan) {
        super(plan.location());
        this.plan = plan;
    }

    public LogicalPlan plan() {
        return plan;
    }

    @Override
    public List<Attribute> output() {
        return plan.output();
    }

    @Override
    public int hashCode() {
        return plan.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UnplannedExec other = (UnplannedExec) obj;
        return Objects.equals(plan, other.plan);
    }

    @Override
    public String nodeString() {
        return nodeName() + "[" + plan.nodeString() + "]";
    }
}
package org.thinkit.generator.rule.factory.strategy.dtogenerator;

import java.util.List;

import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorStrategy;

import lombok.NonNull;

public class DtoCopyingConstructorStrategy extends ConstructorStrategy {

    @Override
    public String toParameter(@NonNull List<Parameter> parameters) {
        return null;
    }

    @Override
    public String toProcess(@NonNull List<Process> processes) {
        return null;
    }
}
/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.command.runtime.rule;

import org.drools.command.Context;
import org.drools.command.WorkingMemoryEntryPointBuilder;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

public class GetWorkingMemoryEntryPointCommand
    implements
    GenericCommand<WorkingMemoryEntryPoint> {

    private String name;

    public GetWorkingMemoryEntryPointCommand(String name) {
        this.name = name;
    }

    public WorkingMemoryEntryPoint execute(Context context) {
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        WorkingMemoryEntryPoint ep = ksession.getWorkingMemoryEntryPoint(name);
        if (ep == null) {
            return null;
        }

        WorkingMemoryEntryPointBuilder epBuilder = (WorkingMemoryEntryPointBuilder)context.get(WorkingMemoryEntryPointBuilder.class.getName());
        return epBuilder != null ? epBuilder.getWorkingMemoryEntryPoint(name) : ep;
    }

    public String toString() {
        return "session.getWorkingMemoryEntryPoint( " + name + " );";
    }
}
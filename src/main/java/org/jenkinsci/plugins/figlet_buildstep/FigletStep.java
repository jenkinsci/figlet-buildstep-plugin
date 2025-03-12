package org.jenkinsci.plugins.figlet_buildstep;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.inject.Inject;
import hudson.Extension;
import hudson.model.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.Serial;

/**
 * Created by acearl on 10/29/2015.
 */
public class FigletStep extends AbstractStepImpl {

    public final String message;

    @DataBoundConstructor
    public FigletStep(String message) {
        this.message = message;
    }

    public static class FigletStepExecution extends AbstractSynchronousNonBlockingStepExecution<Void> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Inject
        private transient FigletStep step;

        @StepContextParameter
        private transient TaskListener listener;

        @Override
        protected Void run() throws Exception {
            if(StringUtils.isNotEmpty(step.message)) {
                for(String line : step.message.split("\r?\n")) {
                    listener.getLogger().println(FigletFont.convertOneLine(line));
                }
            }
            return null;
        }
    }

    @Extension
    public static final class DescriptorImpl extends AbstractStepDescriptorImpl {
        public DescriptorImpl() {
            super(FigletStepExecution.class);
        }

        @Override
        public String getFunctionName() {
            return "figlet";
        }

        @Override
        public String getDisplayName() {
            return "Figlet";
        }
    }
}

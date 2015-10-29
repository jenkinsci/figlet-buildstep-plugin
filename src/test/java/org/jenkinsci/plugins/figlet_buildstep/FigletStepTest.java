package org.jenkinsci.plugins.figlet_buildstep;

import org.jenkinsci.plugins.workflow.steps.StepConfigTester;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * Created by acearl on 9/15/2015.
 */
public class FigletStepTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void configRoundTrip() throws Exception {
        FigletStep step1 = new FigletStep("Boo");
        FigletStep step2 = new StepConfigTester(j).configRoundTrip(step1);
        j.assertEqualDataBoundBeans(step1, step2);
    }
}
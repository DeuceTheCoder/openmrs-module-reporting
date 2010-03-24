package org.openmrs.module.reporting.cohort.definition.evaluator;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Cohort;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;


public class EncounterCohortDefinitionEvaluatorTest extends BaseModuleContextSensitiveTest {

	@Before
	public void setup() throws Exception {
		executeDataSet("org/openmrs/module/reporting/include/ReportTestDataset.xml");
	}
	
	/**
     * @see {@link EncounterCohortDefinitionEvaluator#evaluate(CohortDefinition,EvaluationContext)}
     */
    @Test
    @Verifies(value = "should return all patients with encounters if all arguments to cohort definition are empty", method = "evaluate(CohortDefinition,EvaluationContext)")
    public void evaluate_shouldReturnAllPatientsWithEncountersIfAllArgumentsToCohortDefinitionAreEmpty() throws Exception {
	   EncounterCohortDefinition cd = new EncounterCohortDefinition();
	   cd.setEncounterTypeList(new ArrayList<EncounterType>()); // this is a regression test for a NPE on empty lists
	   Cohort c = Context.getService(CohortDefinitionService.class).evaluate(cd, null);
	   Assert.assertEquals(6, c.size());
	   Assert.assertTrue(c.contains(7));
 	   Assert.assertTrue(c.contains(20));
 	   Assert.assertTrue(c.contains(21));
 	   Assert.assertTrue(c.contains(22));
 	   Assert.assertTrue(c.contains(23));
 	   Assert.assertTrue(c.contains(24));
    }

	/**
     * @see {@link EncounterCohortDefinitionEvaluator#evaluate(CohortDefinition,EvaluationContext)}
     */
    @Test
    @Verifies(value = "should return correct patients when all non grouping parameters are set", method = "evaluate(CohortDefinition,EvaluationContext)")
    public void evaluate_shouldReturnCorrectPatientsWhenAllNonGroupingParametersAreSet() throws Exception {
       EncounterCohortDefinition cd = new EncounterCohortDefinition();
 	   cd.setEncounterTypeList(Collections.singletonList(new EncounterType(6)));
 	   cd.setFormList(Collections.singletonList(new Form(1)));
 	   cd.setLocationList(Collections.singletonList(new Location(2)));
 	   cd.setOnOrAfter(DateUtil.getDateTime(2009, 8, 19));
 	   cd.setOnOrBefore(DateUtil.getDateTime(2009, 8, 19));
 	   Cohort c = Context.getService(CohortDefinitionService.class).evaluate(cd, null);
 	   Assert.assertEquals(5, c.size());
 	   Assert.assertTrue(c.contains(20));
 	   Assert.assertTrue(c.contains(21));
 	   Assert.assertTrue(c.contains(22));
 	   Assert.assertTrue(c.contains(23));
 	   Assert.assertTrue(c.contains(24));
    }

	/**
     * @see {@link EncounterCohortDefinitionEvaluator#evaluate(CohortDefinition,EvaluationContext)}
     * 
     */
    @Test
    @Verifies(value = "should return correct patients when all parameters are set", method = "evaluate(CohortDefinition,EvaluationContext)")
    public void evaluate_shouldReturnCorrectPatientsWhenAllParametersAreSet() throws Exception {
        EncounterCohortDefinition cd = new EncounterCohortDefinition();
        cd.setEncounterTypeList(Collections.singletonList(new EncounterType(6)));
        cd.setFormList(Collections.singletonList(new Form(1)));
        cd.setLocationList(Collections.singletonList(new Location(2)));
        cd.setOnOrAfter(DateUtil.getDateTime(2009, 8, 19));
        cd.setOnOrBefore(DateUtil.getDateTime(2009, 8, 19));
        cd.setAtLeastCount(2);
        cd.setAtMostCount(2);
        Cohort c = Context.getService(CohortDefinitionService.class).evaluate(cd, null);
        Assert.assertEquals(2, c.size());
        Assert.assertTrue(c.contains(21));
        Assert.assertTrue(c.contains(22));
    }
	

}

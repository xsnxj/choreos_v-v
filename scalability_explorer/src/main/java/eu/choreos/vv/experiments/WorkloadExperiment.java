package eu.choreos.vv.experiments;

import java.util.ArrayList;
import java.util.List;

import eu.choreos.vv.increasefunctions.ScalabilityFunction;
import eu.choreos.vv.loadgenerator.LoadGenerator;

/**
 * This class implements a skeleton of a workload experiment consisted on
 * many steps. In each test battery, the frequency of requests and the quantity
 * of resources will be increased according to a ScalabilityFunction. A request
 * is executed a number of times, in each step, and some metrics are collected
 * for analysis. The steps will be executed up to a determined number of
 * executions (or until one's aggregated return value surpasses a defined
 * limit).
 * 
 */
public abstract class WorkloadExperiment extends Experiment {

	private Number initialRequestsPerMinute;


	/**
	 * Creates a new ScalabilityTester that uses UniformLoadGenarator, Mean and
	 * LinearIncrease
	 */
	public WorkloadExperiment() {
		super();
		init();
	}

	/**
	 * Creates a new ScalabilityTester
	 * 
	 * @param loadGenerator
	 *            load generator to run the tests
	 * @param aggregator
	 *            aggregation function
	 * @param function
	 *            scalability function
	 */
	public WorkloadExperiment(LoadGenerator loadGenerator, ScalabilityFunction function) {
		super(loadGenerator, function);
		init();
	}

	private void init() {
		this.initialRequestsPerMinute = 60;
		List<String> labels = new ArrayList<String>();
		labels.add("load");
		super.setParameterLabels(labels);
	}

	public Number getInitialRequestsPerMinute() {
		return initialRequestsPerMinute;
	}

	public void setInitialRequestsPerMinute(Number initialRequestsPerMinute) {
		this.initialRequestsPerMinute = initialRequestsPerMinute;
	}

	@Override
	protected Number[] getInitialParameterValues() {
		Number[] values = new Number[1];
		values[0] = initialRequestsPerMinute;
		return values;
	}
	
	@Override
	protected void updateParameterValues(Number... values) {
		super.setNumberOfRequestsPerMinute(values[0].intValue());
		setDeployer(null);
	}
		
}

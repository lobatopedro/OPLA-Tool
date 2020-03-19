package br.ufpr.dinf.gres.core.jmetal4.experiments;

public interface AlgorithmBase<T extends ExperimentCommomConfigs> {

    /**
     * This method must call the experiment class, or implement the experiment code
     */

    void run(T experimentCommomConfigs);
}
package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class JobsResponse {
    private List<Job> jobs = null;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public JobsResponse jobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public JobsResponse addJobsItem(Job jobsItem) {
        if (this.jobs == null) {
            this.jobs = new ArrayList<Job>();
        }
        this.jobs.add(jobsItem);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobsResponse)) return false;

        JobsResponse jobs1 = (JobsResponse) o;
        return new EqualsBuilder()
                .append(jobs, jobs1.jobs)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(jobs)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobs=" + jobs +
                '}';
    }
}

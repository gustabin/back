package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.Date;

public class Job {

    private static final String UNEXPECTED_VALUE_ERROR = "Unexpected value ";

    private String companyName;
    private CompanyTypeEnum companyType;
    private String activitySector;
    private Float income;
    private PositionEnum position;
    private Boolean isEmployee;
    private WorkRelationshipTypeEnum workRelationshipType;
    private ActivityTypeEnum activityType;
    private Date incomeUpdatedAt;
    private Date startDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyTypeEnum getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyTypeEnum companyType) {
        this.companyType = companyType;
    }

    public String getActivitySector() {
        return activitySector;
    }

    public void setActivitySector(String activitySector) {
        this.activitySector = activitySector;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public WorkRelationshipTypeEnum getWorkRelationshipType() {
        return workRelationshipType;
    }

    public void setWorkRelationshipType(WorkRelationshipTypeEnum workRelationshipType) {
        this.workRelationshipType = workRelationshipType;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public Date getIncomeUpdatedAt() {
        return incomeUpdatedAt;
    }

    public void setIncomeUpdatedAt(Date incomeUpdatedAt) {
        this.incomeUpdatedAt = incomeUpdatedAt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //Chained setters methods
    public Job companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Job companyType(CompanyTypeEnum companyType) {
        this.companyType = companyType;
        return this;
    }

    public Job activitySector(String activitySector) {
        this.activitySector = activitySector;
        return this;
    }

    public Job income(Float income) {
        this.income = income;
        return this;
    }

    public Job position(PositionEnum position) {
        this.position = position;
        return this;
    }

    public Job isEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
        return this;
    }

    public Job workRelationshipType(WorkRelationshipTypeEnum workRelationshipType) {
        this.workRelationshipType = workRelationshipType;
        return this;
    }

    public Job activityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
        return this;
    }

    public Job incomeUpdatedAt(Date incomeUpdatedAt) {
        this.incomeUpdatedAt = incomeUpdatedAt;
        return this;
    }

    public Job startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public enum CompanyTypeEnum {
        @SerializedName("large_company")
        LARGE_COMPANY("LARGE_COMPANY"),

        @SerializedName("microenterprise")
        MICROENTERPRISE("MICROENTERPRISE"),

        @SerializedName("public_body")
        PUBLIC_BODY("PUBLIC_BODY"),

        @SerializedName("other")
        OTHER("OTHER"),

        @SerializedName("small_and_midsize_enterprise")
        SMALL_AND_MIDSIZE_ENTERPRISE("SMALL_AND_MIDSIZE_ENTERPRISE");

        private final String value;

        CompanyTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CompanyTypeEnum fromValue(String value) {
            for (CompanyTypeEnum b : CompanyTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(UNEXPECTED_VALUE_ERROR + "'" + value + "'");
        }
    }

    public enum PositionEnum {
        @SerializedName("merchant_with_employees")
        MERCHANT_WITH_EMPLOYEES("MERCHANT_WITH_EMPLOYEES"),

        @SerializedName("professional_with_employees")
        PROFESSIONAL_WITH_EMPLOYEES("PROFESSIONAL_WITH_EMPLOYEES"),

        @SerializedName("businessman_with_employees")
        BUSINESSMAN_WITH_EMPLOYEES("BUSINESSMAN_WITH_EMPLOYEES"),

        @SerializedName("merchant_without_employees")
        MERCHANT_WITHOUT_EMPLOYEES("MERCHANT_WITHOUT_EMPLOYEES"),

        @SerializedName("professional_without_employees")
        PROFESSIONAL_WITHOUT_EMPLOYEES("PROFESSIONAL_WITHOUT_EMPLOYEES"),

        @SerializedName("self_employed_without_employees")
        SELF_EMPLOYED_WITHOUT_EMPLOYEES("SELF_EMPLOYED_WITHOUT_EMPLOYEES"),

        @SerializedName("senior_manager")
        SENIOR_MANAGER("SENIOR_MANAGER"),

        @SerializedName("middle_manager")
        MIDDLE_MANAGER("MIDDLE_MANAGER"),

        @SerializedName("employee")
        EMPLOYEE("EMPLOYEE"),

        @SerializedName("laborer")
        LABORER("LABORER"),

        @SerializedName("homemaker")
        HOMEMAKER("HOMEMAKER"),

        @SerializedName("student")
        STUDENT("STUDENT"),

        @SerializedName("retired")
        RETIRED("RETIRED"),

        @SerializedName("rentier")
        RENTIER("RENTIER"),

        @SerializedName("financial_manager")
        FINANCIAL_MANAGER("FINANCIAL_MANAGER"),

        @SerializedName("financial_assistant_manager")
        FINANCIAL_ASSISTANT_MANAGER("FINANCIAL_ASSISTANT_MANAGER"),

        @SerializedName("boss")
        BOSS("BOSS"),

        @SerializedName("operator")
        OPERATOR("OPERATOR"),

        @SerializedName("manager_portfolio")
        MANAGER_PORTFOLIO("MANAGER_PORTFOLIO"),

        @SerializedName("president")
        PRESIDENT("PRESIDENT"),

        @SerializedName("managing_partner")
        MANAGING_PARTNER("MANAGING_PARTNER"),

        @SerializedName("administration")
        ADMINISTRATION("ADMINISTRATION"),

        @SerializedName("treasury")
        TREASURY("TREASURY"),

        @SerializedName("human_resources")
        HUMAN_RESOURCES("HUMAN_RESOURCES");

        private final String value;

        PositionEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static PositionEnum fromValue(String value) {
            for (PositionEnum b : PositionEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(UNEXPECTED_VALUE_ERROR + "'" + value + "'");
        }
    }

    public enum WorkRelationshipTypeEnum {
        @SerializedName("hired")
        HIRED("HIRED"),

        @SerializedName("permanent")
        PERMANENT("PERMANENT");

        private final String value;

        WorkRelationshipTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static WorkRelationshipTypeEnum fromValue(String value) {
            for (WorkRelationshipTypeEnum b : WorkRelationshipTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(UNEXPECTED_VALUE_ERROR + "'" + value + "'");
        }
    }

    public enum ActivityTypeEnum {
        @SerializedName("self_employment")
        SELF_EMPLOYMENT("SELF_EMPLOYMENT"),

        @SerializedName("none")
        NONE("NONE"),

        @SerializedName("dependency_relationship")
        DEPENDENCY_RELATIONSHIP("DEPENDENCY_RELATIONSHIP");

        private final String value;

        ActivityTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ActivityTypeEnum fromValue(String value) {
            for (ActivityTypeEnum b : ActivityTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(UNEXPECTED_VALUE_ERROR + "'" + value + "'");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;
        return new EqualsBuilder()
                .append(companyName, job.companyName)
                .append(companyType, job.companyType)
                .append(activitySector, job.activitySector)
                .append(income, job.income)
                .append(position, job.position)
                .append(isEmployee, job.isEmployee)
                .append(workRelationshipType, job.workRelationshipType)
                .append(activityType, job.activityType)
                .append(incomeUpdatedAt, job.incomeUpdatedAt)
                .append(startDate, job.startDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(companyName)
                .append(companyType)
                .append(activitySector)
                .append(income)
                .append(position)
                .append(isEmployee)
                .append(workRelationshipType)
                .append(activityType)
                .append(incomeUpdatedAt)
                .append(startDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Job{" +
                "companyName='" + companyName + '\'' +
                ", companyType=" + companyType +
                ", activitySector='" + activitySector + '\'' +
                ", income=" + income +
                ", position=" + position +
                ", isEmployee=" + isEmployee +
                ", workRelationshipType=" + workRelationshipType +
                ", activityType=" + activityType +
                ", incomeUpdatedAt=" + incomeUpdatedAt +
                ", startDate=" + startDate +
                '}';
    }
}

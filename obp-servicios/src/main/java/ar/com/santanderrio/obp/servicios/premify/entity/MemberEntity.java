package ar.com.santanderrio.obp.servicios.premify.entity;

import org.beanio.annotation.Field;

public class MemberEntity {

    @Field
    private Integer points = 0;

    public MemberEntity(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}

package com.bizsoft.fmcgv2.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by GopiKing on 28-12-2017.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountGroup {

    Long Id;
    String GroupName;
    Long UnderGroupId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public Long getUnderGroupId() {
        return UnderGroupId;
    }

    public void setUnderGroupId(Long underGroupId) {
        UnderGroupId = underGroupId;
    }
}

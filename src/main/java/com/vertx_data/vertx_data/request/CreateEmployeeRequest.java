
package com.vertx_data.vertx_data.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CreateEmployeeRequest {

    @Expose
    private String contact;
    @Expose
    private Long dateOfBirth;
    @Expose
    private Long dateOfJoining;
    @Expose
    private String gender;
    @Expose
    private String name;

    public String getContact() {
        return contact;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getDateOfJoining() {
        return dateOfJoining;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }


}

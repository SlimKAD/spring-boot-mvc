package com.slim.springbootrestfulservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
    return new PersonV1("maria");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2("maria", "doe");
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 param1() {
        return new PersonV1("maria");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 param2() {
        return new PersonV2("maria", "doe");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 header1() {
        return new PersonV1("maria");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 header2() {
        return new PersonV2("maria", "doe");
    }

}

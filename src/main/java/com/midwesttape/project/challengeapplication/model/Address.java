package com.midwesttape.project.challengeapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    long addressId;
    String address1;
    String address2;
    String city;
    String state;
    String postal;
}

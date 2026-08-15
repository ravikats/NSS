package com.empay.irfservice.callback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRFCallbackRepository extends JpaRepository<IRFCallbackEntity, Integer> {

    List<IRFCallbackEntity> findByInstitutionCodeAndJobNumberAndStatusOrderBySerialNumber(
            Integer insCode, Integer jobNumber, Character status);

    List<IRFCallbackEntity> findByStatusOrderBySerialNumber(Character status);
}

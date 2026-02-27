package com.visionforge.domain.exception;

import com.visionforge.domain.enums.JobStatus;

public class InvalidJobStateTransitionException extends RuntimeException {
    public InvalidJobStateTransitionException(JobStatus currentStatus, JobStatus targetStatus) {
        super(String.format("Não é possível transitar do estado %s para %s", currentStatus, targetStatus));
    }
}
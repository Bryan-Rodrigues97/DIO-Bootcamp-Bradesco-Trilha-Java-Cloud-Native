package com.dio.design_patterns.service.approvals.interfaces;

import com.dio.design_patterns.entity.User;

public interface CheckUserApproval {
    boolean check(User user);
}

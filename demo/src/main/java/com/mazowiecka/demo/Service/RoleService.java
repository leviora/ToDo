package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Role;

public interface RoleService {

    void initializeRoles();

    Role findOrCreateRole(Role.RoleName roleName);

    Role getRoleByName(Role.RoleName roleName);
}

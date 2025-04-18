package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Repository.RoleRepository;
import com.mazowiecka.demo.Service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeRoles() {
        findOrCreateRole(Role.RoleName.ROLE_USER);
        findOrCreateRole(Role.RoleName.ROLE_ADMIN);
    }

    @Override
    public Role findOrCreateRole(Role.RoleName roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return roleRepository.save(role);
                });
    }

    @Override
    public Role getRoleByName(Role.RoleName roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Rola " + roleName + " nie istnieje"));
    }
}

package com.formation.pharmacy_manager.services.pharmacistService;

import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistRequestDto;
import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistResponseDto;
import com.formation.pharmacy_manager.entities.Pharmacist;
import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.enumEntities.Type;
import com.formation.pharmacy_manager.repository.PharmacistRepository;
import com.formation.pharmacy_manager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class PharmacistSeviceImpl implements PharmacistSevice {
    private PharmacistRepository pharmacistRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public PharmacistResponseDto create(PharmacistRequestDto dto) {
        Pharmacist phar = new Pharmacist();
        phar.setUserName(dto.getUserName());
        phar.setEmail(dto.getEmail());
        phar.setPhoneNumber(dto.getPhoneNumber());
        phar.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = roleRepository.getByType(Type.valueOf(dto.getRole()));
        phar.getRoles().add(role);
        phar.setCreation_date(LocalDate.now());
        phar.setUpdate_date(new Date());
        Pharmacist saved = pharmacistRepository.save(phar);
        return new PharmacistResponseDto(
                saved.getUserId(),
                saved.getUserName(),
                saved.getPhoneNumber(),
                saved.getEmail(),
                saved.getCreation_date(),
                saved.getUpdate_date()
        );
    }

    @Override
    public List<PharmacistResponseDto> getAllPharmacist() {
        return pharmacistRepository.findAll().stream().map(
                phar->new PharmacistResponseDto(
                      phar.getUserId(),
                      phar.getUserName(),
                      phar.getPhoneNumber(),
                      phar.getEmail(),
                      phar.getCreation_date(),
                      phar.getUpdate_date()
                )
        ).toList();
    }

    @Override
    public String deleteById(long id) {
        Pharmacist phar = pharmacistRepository.findById(id).get();

        if(phar == null) throw new RuntimeException("impossible to delete : because this pharmacist doesn't exist");
        pharmacistRepository.deleteById(id);
        return "pharmacist : "+phar.getUserName()+" "+phar.getEmail()+" was deleting successfully";
    }

    @Override
    public PharmacistResponseDto update(long id, PharmacistRequestDto dto) {

        Pharmacist phar =pharmacistRepository.findById(id).orElseThrow();
        phar.setUserName(dto.getUserName());
        phar.setEmail(dto.getEmail());
        phar.setPhoneNumber(dto.getPhoneNumber());
        phar.setPassword(dto.getPassword());
        phar.setUpdate_date(new Date());

        Pharmacist saved = pharmacistRepository.save(phar);
        return new PharmacistResponseDto(
                saved.getUserId(),
                saved.getUserName(),
                saved.getPhoneNumber(),
                saved.getEmail(),
                saved.getCreation_date(),
                saved.getUpdate_date()
        );
    }
}

package com.fitnesstracker.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesstracker.dao.GenderRefRepository;
import com.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.dto.BMIResultDTO;
import com.fitnesstracker.dto.LoginRequestDTO;
import com.fitnesstracker.dto.LoginResponseDTO;
import com.fitnesstracker.dto.UserReportDTO;
import com.fitnesstracker.dto.UsersDTO;
import com.fitnesstracker.entity.GenderRef;
import com.fitnesstracker.entity.Users;
import com.fitnesstracker.exception.EmailAlreadyExistsException;
import com.fitnesstracker.exception.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private  GenderRefRepository genderRefRepository;
	
	public boolean emailExists(String email) {
		return userRepository.existsByUserEmail(email);
	}
	
	 public  UsersDTO usersEntityToDTO(Users users) {
	        UsersDTO usersDTO = new UsersDTO();

	        usersDTO.setId(users.getId());
	        usersDTO.setUserName(users.getUserName());
	        usersDTO.setUserPassword(users.getUserPassword());
	        usersDTO.setGenderRefId(users.getGenderRefId() != null ? users.getGenderRefId().getRefId() : null);
	        usersDTO.setUserAge(users.getUserAge());
	        usersDTO.setUserHeight(users.getUserHeight());
	        usersDTO.setUserWeight(users.getUserWeight());
	        usersDTO.setUserEmail(users.getUserEmail());

	        return usersDTO;
	    }
	 
	 public  Users usersDTOToEntity(UsersDTO usersDTO) {
	        Users users = new Users();
	        
	        users.setId(usersDTO.getId());
	        users.setUserName(usersDTO.getUserName());
	        users.setUserPassword(usersDTO.getUserPassword());
	        // Fetch GenderRef entity using genderId from UsersDTO
	        if (usersDTO.getGenderRefId() != null) {
	            GenderRef genderRef = genderRefRepository.findById(usersDTO.getGenderRefId())
	                                    .orElseThrow(() -> new IllegalArgumentException("Invalid Gender ID: " + usersDTO.getGenderRefId()));
	            users.setGenderRefId(genderRef);
	        }
	        users.setUserAge(usersDTO.getUserAge());
	        users.setUserHeight(usersDTO.getUserHeight());
	        users.setUserWeight(usersDTO.getUserWeight());
	        users.setUserEmail(usersDTO.getUserEmail());
	  
	        return users;
	    }
	

	

    public UsersDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::usersEntityToDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public UsersDTO addUser(UsersDTO userDTO) {
        // Check if email already exists
        if (userRepository.findByUserEmail(userDTO.getUserEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + userDTO.getUserEmail() + " already exists");
        }

        // Map DTO to Entity
        Users user = usersDTOToEntity(userDTO);

        // Save the user entity
        user = userRepository.save(user);

        // Convert Entity back to DTO and return
        return usersEntityToDTO(user);
    }

    public UsersDTO updateUser(UsersDTO userDTO) {
        Long id = userDTO.getId(); 
        
        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        
     // Map DTO to Entity
        Users user = usersDTOToEntity(userDTO);

        // Save the user entity
        user = userRepository.save(user);

        // Convert Entity back to DTO and return
        return usersEntityToDTO(user);
    }
    
    
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {
        // Fetch user by email
        Optional<Users> userOptional = userRepository.findByUserEmail(loginRequestDTO.getUserEmail());
        
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            
            // Validate password (You should hash and compare hashed passwords in production)
            if (user.getUserPassword().equals(loginRequestDTO.getUserPassword())) {
                // Create and return response DTO
                LoginResponseDTO responseDTO = new LoginResponseDTO();
                responseDTO.setId(user.getId());
                responseDTO.setUserName(user.getUserName());
                responseDTO.setUserEmail(user.getUserEmail());
                return responseDTO;
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("User not found with the provided email");
        }
    }
    
    public BMIResultDTO calculateBMI(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        double heightInMeters = user.getUserHeight() / 100.0; // Convert height from cm to meters
        double weightInKg = user.getUserWeight();

        if (heightInMeters <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero.");
        }

        double bmi = weightInKg / (heightInMeters * heightInMeters);

        // Round BMI to 2 decimal places
        BigDecimal bmiRounded = new BigDecimal(bmi).setScale(2, RoundingMode.HALF_UP);

        // Determine health status based on BMI
        String healthStatus = determineHealthStatus(bmiRounded.doubleValue());

        return new BMIResultDTO(userId, user.getUserName(), bmiRounded.doubleValue(), healthStatus);
    }

    private String determineHealthStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
    
    @Transactional
    public Optional<UserReportDTO> getUserReportByUserIdAndDate(Long userId, LocalDate date) {
        // Execute the query
        Object[] result = userRepository.findUserReportByUserIdAndDate(userId, date.toString());
        
        // Check if result is present
        if (result != null && result.length == 5) {
            // Cast and map the result to UserReportDTO
            UserReportDTO userReportDTO = new UserReportDTO();
            userReportDTO.setUserId((Long) result[0]);
            userReportDTO.setUserName((String) result[1]);
            userReportDTO.setReportDate((LocalDate) result[2]);
            userReportDTO.setTotalCaloriesConsumed((Integer) result[3]);
            userReportDTO.setTotalCaloriesBurned((Integer) result[4]);
            
            return Optional.of(userReportDTO);
        }

        // If no result, return an empty Optional
        return Optional.empty();
    }
}

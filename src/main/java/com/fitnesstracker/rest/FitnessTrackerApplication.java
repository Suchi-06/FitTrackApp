package com.fitnesstracker.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesstracker.dto.ActivityTypeDTO;
import com.fitnesstracker.dto.BMIResultDTO;
import com.fitnesstracker.dto.GoalResponseDTO;
import com.fitnesstracker.dto.GoalsDTO;
import com.fitnesstracker.dto.LoginRequestDTO;
import com.fitnesstracker.dto.LoginResponseDTO;
import com.fitnesstracker.dto.MealsDTO;
import com.fitnesstracker.dto.UserActivityDTO;
import com.fitnesstracker.dto.UserActivityDetailsResponseDTO;
import com.fitnesstracker.dto.UserActivityResponseDTO;
import com.fitnesstracker.dto.UserReportDTO;
import com.fitnesstracker.dto.UsersDTO;
import com.fitnesstracker.exception.ActivityTypeNotFoundException;
import com.fitnesstracker.exception.ResourceNotFoundException;
import com.fitnesstracker.exception.UserNotFoundException;
import com.fitnesstracker.service.ActivityTypeService;
import com.fitnesstracker.service.GoalService;
import com.fitnesstracker.service.MealService;
import com.fitnesstracker.service.UserActivityService;
import com.fitnesstracker.service.UserService;
import org.slf4j.Logger;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@Validated
@RequestMapping("/api")
public class FitnessTrackerApplication {
	
	private static final Logger log = LoggerFactory.getLogger(FitnessTrackerApplication.class); // Create Logger

	@Autowired
    private ActivityTypeService activityTypeService;
	@Autowired
    private UserService userService;
	@Autowired
    private UserActivityService userActivityService;
	@Autowired
	private GoalService goalService;
	@Autowired
	private MealService mealService;
	
	 public FitnessTrackerApplication(ActivityTypeService activityTypeService, UserService userService,
			UserActivityService userActivityService, GoalService goalService, MealService mealService) {
		super();
		this.activityTypeService = activityTypeService;
		this.userService = userService;
		this.userActivityService = userActivityService;
		this.goalService = goalService;
		this.mealService = mealService;
	}
	 @GetMapping("/checkuseremail")
		public boolean checkEmail(@RequestParam final String userEmail) {
			boolean isUnique = userService.emailExists(userEmail);
			return isUnique;
		}

	 @PostMapping("/login")
	    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
	        try {
	            log.info("Attempting to login with email: {}", loginRequestDTO.getUserEmail());
	            LoginResponseDTO responseDTO = userService.login(loginRequestDTO);
	            log.info("Login successful for email: {}", loginRequestDTO.getUserEmail());
	            return ResponseEntity.ok(responseDTO);
	        } catch (Exception e) {
	            log.error("Login failed for email: {}", loginRequestDTO.getUserEmail(), e);
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }
	    }

	    @PostMapping("/addactivitytypetolist")
	    public ResponseEntity<ActivityTypeDTO> addActivityType(@RequestBody ActivityTypeDTO activityTypeDTO) {
	        log.info("Adding new activity type: {}", activityTypeDTO.getDescription());
	        ActivityTypeDTO createdActivityType = activityTypeService.addActivityType(activityTypeDTO);
	        return new ResponseEntity<>(createdActivityType, HttpStatus.CREATED);
	    }

	    @GetMapping("/getallActivitiesList")
	    public ResponseEntity<List<ActivityTypeDTO>> findAllActivityTypes() {
	        log.info("Fetching all activity types");
	        List<ActivityTypeDTO> activityTypes = activityTypeService.findAllActivityTypes();
	        return new ResponseEntity<>(activityTypes, HttpStatus.OK);
	    }

	    @GetMapping("/findbyuserid")
	    public ResponseEntity<UsersDTO> getUserById(@RequestParam("userId") Long id) {
	        try {
	            log.info("Fetching user with ID: {}", id);
	            UsersDTO userDTO = userService.getUserById(id);
	            return new ResponseEntity<>(userDTO, HttpStatus.OK);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found with ID: {}", id);
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/registeruser")
	    public ResponseEntity<UsersDTO> addUser(@Valid @RequestBody UsersDTO userDTO) {
	        log.info("Registering new user with email: {}", userDTO.getUserEmail());
	        UsersDTO createdUserDTO = userService.addUser(userDTO);
	        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
	    }

	    @PostMapping("/updateuser")
	    public ResponseEntity<UsersDTO> updateUser(@Valid @RequestBody UsersDTO usersDTO) {
	        try {
	            log.info("Updating user with ID: {}", usersDTO.getId());
	            UsersDTO updatedUserDTO = userService.updateUser(usersDTO);
	            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found for update with ID: {}", usersDTO.getId());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/adduseractivity")
	    public ResponseEntity<UserActivityDTO> addUserActivity(@RequestBody UserActivityDTO userActivityDTO) {
	        try {
	            log.info("Adding user activity for user ID: {}", userActivityDTO.getUserId());
	            UserActivityDTO createdUserActivity = userActivityService.addUserActivity(userActivityDTO);
	            return new ResponseEntity<>(createdUserActivity, HttpStatus.CREATED);
	        } catch (UserNotFoundException | ActivityTypeNotFoundException ex) {
	            log.warn("User or activity type not found while adding user activity: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error adding user activity", ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/goals/addgoal")
	    public ResponseEntity<GoalsDTO> addGoal(@RequestBody GoalsDTO goalsDTO) {
	        try {
	            log.info("Adding goal for user ID: {}", goalsDTO.getUserId());
	            GoalsDTO createdGoal = goalService.addGoal(goalsDTO);
	            return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
	        } catch (UserNotFoundException | ActivityTypeNotFoundException ex) {
	            log.warn("Error adding goal: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error adding goal", ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/meals/addmeal")
	    public ResponseEntity<MealsDTO> addMeal(@RequestBody MealsDTO mealsDTO) {
	        try {
	            log.info("Adding meal for user ID: {}", mealsDTO.getUserId());
	            MealsDTO createdMeal = mealService.addMeal(mealsDTO);
	            return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found while adding meal: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error adding meal", ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/bmi")
	    public ResponseEntity<BMIResultDTO> getBMI(@RequestParam("userId") Long userId) {
	        try {
	            log.info("Calculating BMI for user ID: {}", userId);
	            BMIResultDTO bmiResult = userService.calculateBMI(userId);
	            return ResponseEntity.ok(bmiResult);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found for BMI calculation with ID: {}", userId);
	            return ResponseEntity.notFound().build();
	        } catch (IllegalArgumentException ex) {
	            log.error("Error calculating BMI: Invalid input", ex);
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @GetMapping("/useractivitylistbyuserid")
	    public ResponseEntity<List<UserActivityResponseDTO>> getUserActivitiesByUserId(@RequestParam("userId") Long userId) {
	        try {
	            log.info("Fetching user activities for user ID: {}", userId);
	            List<UserActivityResponseDTO> userActivities = userActivityService.getUserActivities(userId);
	            return new ResponseEntity<>(userActivities, HttpStatus.OK);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (ResourceNotFoundException ex) {
	            log.warn("No activities found for user: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error fetching user activities for userId: {}", userId, ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @GetMapping("/useractivitylistbydate")
	    public ResponseEntity<List<UserActivityDetailsResponseDTO>> getUserActivitiesByDate(@RequestParam("date") String activityDate) {
	        try {
	            log.info("Received request to get user activities by date: {}", activityDate);
	            List<UserActivityDetailsResponseDTO> userActivities = userActivityService.getUserActivitiesByDate(activityDate);
	            return new ResponseEntity<>(userActivities, HttpStatus.OK);
	        } catch (ResourceNotFoundException ex) {
	            log.warn("No user activities found for date: {}", activityDate);
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error fetching user activities for date: {}", activityDate, ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @GetMapping("userreport")
	    public ResponseEntity<?> getUserReport(@RequestParam("userId") Long userId, 
	                                           @RequestParam("date") String date) {
	        try {
	            log.info("Request received for user report with userId: {} and date: {}", userId, date);

	            // Parse the date string to LocalDate
	            LocalDate reportDate;
	            try {
	                reportDate = LocalDate.parse(date);
	            } catch (DateTimeParseException e) {
	                log.error("Invalid date format: {}", date);
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format. Please use 'YYYY-MM-DD'.");
	            }

	            // Fetch the report
	            Optional<UserReportDTO> userReportDTO = userService.getUserReportByUserIdAndDate(userId, reportDate);
	            
	            if (userReportDTO.isPresent()) {
	                log.info("User report retrieved successfully for userId: {} and date: {}", userId, date);
	                return ResponseEntity.ok(userReportDTO.get());
	            } else {
	                log.warn("No report found for userId: {} and date: {}", userId, date);
	                throw new ResourceNotFoundException("No user report found for the given user ID and date.");
	            }

	        } catch (UserNotFoundException ex) {
	            log.error("User not found with userId: {}", userId, ex);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

	        } catch (ResourceNotFoundException ex) {
	            log.warn("No user report found for userId: {} and date: {}", userId, date, ex);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

	        } catch (Exception ex) {
	            log.error("Error occurred while fetching user report for userId: {} and date: {}", userId, date, ex);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the user report.");
	        }
	    }
	    
	    
	    @GetMapping("/findgoalslistbyuserid")
	    public ResponseEntity<List<GoalResponseDTO>> getUserGoals(@RequestParam("userId") Long userId) {
	        log.info("Fetching goals for user ID: {}", userId);
	        try {
	            List<GoalResponseDTO> userGoals = goalService.getGoalsByUserId(userId);
	            return new ResponseEntity<>(userGoals, HttpStatus.OK);
	        } catch (UserNotFoundException ex) {
	            log.warn("User not found: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (ResourceNotFoundException ex) {
	            log.warn("No goals found for user: {}", ex.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            log.error("Error fetching user goals for userId: {}", userId, ex);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}

# vehicle_control_system
This project is an automated testing framework for a theoretical vehicle control system. The system is simulated in Python, and it includes functionalities like acceleration, braking, and steering. Here's an overview:

1. **`VehicleControlSystem` Class**:
   - This class represents a simplified model of a vehicle control system. It has attributes for speed, steering angle, and brake status. Methods include `get_speed` to retrieve the current speed, `accelerate` to simulate acceleration, `brake` to engage the brake, and `steer` to set the steering angle.

2. **`VehicleControlTester` Class**:
   - This class is responsible for testing the functionalities of the vehicle control system. It has three test methods: `test_acceleration`, `test_braking`, and `test_steering`. Each method simulates a scenario and checks if the expected outcome matches the actual outcome.

3. **Test Cases**:
   - `test_acceleration` checks if the vehicle accelerates as expected.
   - `test_braking` checks if the vehicle comes to a complete stop when the brake is applied after acceleration.
   - `test_steering` checks if the steering angle is set correctly.

4. **Main Execution Block**:
   - In this block, an instance of the `VehicleControlSystem` is created. Then, an instance of `VehicleControlTester` is initialized with this system. Finally, the tests are executed using the `run_tests` method.

Please note that this is a simplified example for demonstration purposes. In real-world scenarios, the `VehicleControlSystem` class would interact with actual hardware or software components of a vehicle, and the test cases would be much more complex and cover a wider range of scenarios. Additionally, specialized testing frameworks and tools may be used for more advanced and comprehensive testing.

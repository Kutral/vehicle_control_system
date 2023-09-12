# vehicle_control_system.py

class VehicleControlSystem:
    def __init__(self):
        self.speed = 0
        self.steering_angle = 0
        self.brake_status = False

    def get_speed(self):
        return self.speed

    def accelerate(self, amount):
        self.speed += amount

    def brake(self):
        self.brake_status = True

    def release_brake(self):
        self.brake_status = False

    def steer(self, angle):
        self.steering_angle = angle

# test_vehicle_control.py

class VehicleControlTester:
    def __init__(self, vehicle_control_system):
        self.vehicle_control_system = vehicle_control_system

    def test_acceleration(self):
        initial_speed = self.vehicle_control_system.get_speed()
        self.vehicle_control_system.accelerate(10)
        new_speed = self.vehicle_control_system.get_speed()
        expected_speed = initial_speed + 10

        assert new_speed == expected_speed, f"Acceleration test failed. Expected speed: {expected_speed}, Actual speed: {new_speed}"

    def test_braking(self):
        self.vehicle_control_system.accelerate(20)  # Simulate acceleration first
        self.vehicle_control_system.brake()
        new_speed = self.vehicle_control_system.get_speed()

        assert new_speed == 0, f"Braking test failed. Expected speed: 0, Actual speed: {new_speed}"

    def test_steering(self):
        self.vehicle_control_system.steer(30)
        new_angle = self.vehicle_control_system.steering_angle

        assert new_angle == 30, f"Steering test failed. Expected angle: 30, Actual angle: {new_angle}"

    def run_tests(self):
        self.test_acceleration()
        self.test_braking()
        self.test_steering()

if __name__ == "__main__":
    # Instantiate the vehicle control system
    vehicle_control_system = VehicleControlSystem()

    # Create a tester object
    tester = VehicleControlTester(vehicle_control_system)

    # Run the tests
    tester.run_tests()

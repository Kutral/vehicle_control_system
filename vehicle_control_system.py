import time
import multiprocessing
import pytest
from flask import Flask, render_template, request
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# VehicleControlSystem class
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
        self.speed = 0
        self.brake_status = True

    def release_brake(self):
        self.brake_status = False

    def steer(self, angle):
        self.steering_angle = angle

# Flask application
app = Flask(__name__)
vehicle_control_system = VehicleControlSystem()

@app.route('/', methods=['GET', 'POST'])
def index():
    global vehicle_control_system
    if request.method == 'POST':
        if 'reset' in request.form:
            vehicle_control_system = VehicleControlSystem()
        elif 'accelerate' in request.form:
            amount = int(request.form['amount'])
            vehicle_control_system.accelerate(amount)
        elif 'brake' in request.form:
            vehicle_control_system.brake()
        elif 'steer' in request.form:
            angle = int(request.form['angle'])
            vehicle_control_system.steer(angle)
    speed = vehicle_control_system.get_speed()
    steering_angle = vehicle_control_system.steering_angle
    return render_template_string('''
<html>
<body>
    <p>Speed: <span id="speed">{{ speed }}</span></p>
    <p>Steering Angle: <span id="steering_angle">{{ steering_angle }}</span></p>
    <form method="post">
        <input type="number" name="amount" placeholder="Acceleration amount">
        <button type="submit" name="accelerate">Accelerate</button>
    </form>
    <form method="post">
        <button type="submit" name="brake">Brake</button>
    </form>
    <form method="post">
        <input type="number" name="angle" placeholder="Steering angle">
        <button type="submit" name="steer">Steer</button>
    </form>
    <form method="post">
        <button type="submit" name="reset">Reset</button>
    </form>
</body>
</html>
''', speed=speed, steering_angle=steering_angle)

# Function to run Flask app
def run_flask():
    app.run(port=5000, use_reloader=False)

# Pytest fixture for Flask and Selenium
@pytest.fixture(scope='module')
def selenium_driver():
    flask_process = multiprocessing.Process(target=run_flask)
    flask_process.start()
    time.sleep(2)  # Wait for server to start
    driver = webdriver.Chrome()
    yield driver
    driver.quit()
    flask_process.terminate()
    flask_process.join()

# Pytest test functions
def test_acceleration(selenium_driver):
    driver = selenium_driver
    driver.get('http://localhost:5000')
    driver.find_element(By.NAME, 'reset').click()
    driver.find_element(By.NAME, 'amount').send_keys('10')
    driver.find_element(By.NAME, 'accelerate').click()
    WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, 'speed'), '10'))
    assert driver.find_element(By.ID, 'speed').text == '10'

def test_braking(selenium_driver):
    driver = selenium_driver
    driver.get('http://localhost:5000')
    driver.find_element(By.NAME, 'reset').click()
    driver.find_element(By.NAME, 'amount').send_keys('20')
    driver.find_element(By.NAME, 'accelerate').click()
    WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, 'speed'), '20'))
    driver.find_element(By.NAME, 'brake').click()
    WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, 'speed'), '0'))
    assert driver.find_element(By.ID, 'speed').text == '0'

def test_steering(selenium_driver):
    driver = selenium_driver
    driver.get('http://localhost:5000')
    driver.find_element(By.NAME, 'reset').click()
    driver.find_element(By.NAME, 'angle').send_keys('30')
    driver.find_element(By.NAME, 'steer').click()
    WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, 'steering_angle'), '30'))
    assert driver.find_element(By.ID, 'steering_angle').text == '30'

if __name__ == '__main__':
    pytest.main([__file__])

# ========================================
# Local Development Run Script
# ========================================
# This script sets environment variables and runs the application locally.
# Edit the values below to match your local setup.

$env:SPRING_PROFILES_ACTIVE = "local"
$env:DB_URL = "jdbc:postgresql://localhost:5432/local_akano"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "123456"

Write-Host "Running with profile: $env:SPRING_PROFILES_ACTIVE" -ForegroundColor Cyan

.\mvnw spring-boot:run

# Import the reusable function
. ./RunJavaApp.ps1

# Parameters for the server application
$mainClass = "uopeople.assignment.unit7.server.Main"
$outputPath = "./bin"
$sourceDir = "./src/uopeople/assignment/unit7"

# Run the ChatServer
Run-JavaApp -MainClass $mainClass -OutputPath $outputPath -SourceDir $sourceDir

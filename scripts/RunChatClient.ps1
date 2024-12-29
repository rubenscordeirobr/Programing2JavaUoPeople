# Import the reusable function
. ./RunJavaApp.ps1

# Parameters for the client application
$mainClass = "uopeople.assignment.unit7.client.Main"
$outputPath = "../bin"
$sourceDir = "../src/uopeople/assignment/unit7"

# Run the ChatClient
Run-JavaApp -MainClass $mainClass -OutputPath $outputPath -SourceDir $sourceDir

<#
.SYNOPSIS
Compiles and runs a Java application.

.PARAMETER MainClass
The fully qualified name of the main class to run.

.PARAMETER OutputPath
The path where compiled .class files are stored.

.PARAMETER SourceDir
The directory containing .java source files.

#>
function Run-JavaApp {
    
    param (
        [string]$MainClass,
        [string]$OutputPath = "../bin",
        [string]$SourceDir = "../src"
    )

    # Paths to Java tools
    $javaPath = "C:\Program Files\Eclipse Adoptium\jdk-17.0.12.7-hotspot\bin\java.exe"
    $javacPath = "C:\Program Files\Eclipse Adoptium\jdk-17.0.12.7-hotspot\bin\javac.exe"

    # Create the bin directory if it doesn't exist
    if (!(Test-Path -Path $OutputPath)) {
        Write-Host "Creating output directory..."
        New-Item -ItemType Directory -Force -Path $OutputPath
    }

    # Compile Java files
    Write-Host "Compiling Java files..."
    $javaFiles = Get-ChildItem -Recurse -Filter *.java -Path $SourceDir | ForEach-Object { $_.FullName }
    & $javacPath -d $OutputPath $javaFiles
    if ($LASTEXITCODE -ne 0) {
        Write-Error "Compilation failed. Please check your Java source files."
        exit 1
    }

    # Run the Java application
    Write-Host "Starting Java application: $MainClass"
    & $javaPath -cp $OutputPath $MainClass
}

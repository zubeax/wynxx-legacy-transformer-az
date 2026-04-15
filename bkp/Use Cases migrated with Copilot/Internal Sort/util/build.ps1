Param()
Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    Write-Host "Compiling SortDta.java..."
    & javac SortDta.java
    Write-Host "Done."
} finally {
    Pop-Location
}
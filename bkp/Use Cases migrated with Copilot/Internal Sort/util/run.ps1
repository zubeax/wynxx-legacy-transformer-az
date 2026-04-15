Param(
    [Parameter(Mandatory=$true)][string]$InputFile,
    [Parameter(Mandatory=$true)][string]$OutputFile
)
Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    if (-not (Test-Path .\SortDta.class)) { .uild.ps1 }
    & java SortDta $InputFile $OutputFile
} finally {
    Pop-Location
}
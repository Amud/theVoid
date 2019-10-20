fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android rollout
```
fastlane android rollout
```
Submit a new bundle to Play store Alpha
### android update_rollout
```
fastlane android update_rollout
```
Update rollout of build
### android promot
```
fastlane android promot
```
Promot a build from_track  to_track
### android assemble
```
fastlane android assemble
```
Build project
### android code_freeze
```
fastlane android code_freeze
```
code freeze
### android notes
```
fastlane android notes
```
release notes
### android release_announce
```
fastlane android release_announce
```
release announcement
### android promot_announce
```
fastlane android promot_announce
```
build promotion announcement
### android collect_assets
```
fastlane android collect_assets
```
collect release assets
### android send_mail
```
fastlane android send_mail
```
sending email
### android upload_asset
```
fastlane android upload_asset
```
uploading assets on s3

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).

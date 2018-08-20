c:\Initium\Slack_Notifier.exe --url="https://hooks.slack.com/services/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" --username="Deployment Notifier" --pretext="Starting new image and audio deployment.." --text=" "


git -C c:\Initium\Initium-Resources pull

cd "C:\Program Files\Java\jdk1.7.0\bin"

call C:\appengine-java-sdk-1.9.36\appengine-java-sdk-1.9.36\bin\appcfg.cmd -V 1 update c:\Initium\Initium-Resources\war

c:\Initium\Slack_Notifier.exe --url="https://hooks.slack.com/services/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" --username="Deployment Notifier" --pretext="Deployment completed successfully." --text=" "

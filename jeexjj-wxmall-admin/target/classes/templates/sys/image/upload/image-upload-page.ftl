<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>WebUploader演示</title>
    <link rel="stylesheet" type="text/css" href="${base}/assets/webuploader-0.1.5/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${base}/assets/webuploader-0.1.5/style.css" />
	<link rel="stylesheet" href="${base}/assets/layer-v3.1.1/layer/theme/default/layer.css">
</head>
<body>
    <div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${base}/assets/webuploader-0.1.5/jquery.js"></script>
    <script src="${base}/assets/layer-v3.1.1/layer/layer.js"></script>
    <script src="${base}/assets/xjj/xjj.util.js"></script>
    <script type="text/javascript" src="${base}/assets/webuploader-0.1.5/webuploader.js"></script>
    <script type="text/javascript" src="${base}/assets/webuploader-0.1.5/upload.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グラフ表示</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>

	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="top">家計簿支援システム</a>
			</h1>
			<div class="user">
				<p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
				<form class="logout_form" action="logout" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png" />ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>
	</div>

	<div style="position: relative; width: 400px; height: 400px; margin: auto;">
		<canvas id="myChart"></canvas>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
	
	
		
	const data = {
			  labels: '${labelData}'.replace(']', '').replace('[', '').split(','),
			  datasets: [{
			    label: 'My First Dataset',
			    data: ${sumAmountData},
			    backgroundColor: [
			      'rgb(255, 99, 132)',
			      'rgb(54, 162, 235)',
			      'rgb(255, 205, 86)',
			      'rgb(30, 255, 30)',
			      'rgb(255, 200, 233)',
			      'rgb(200, 200, 255)',
			      'rgb(255, 255, 100)',
			      'rgb(181, 255, 20)'
			    ],
			    hoverOffset: 4
			  }]
			};

		const config = {
			type : 'doughnut',
			data : data,
		};
	</script>
	<script>
		const myChart = new Chart(document.getElementById('myChart'), config);
	</script>
	<br>
	<div class="btns">
		<a href="top" class="cancel_btn">戻る</a>
	</div>
</body>
</html>
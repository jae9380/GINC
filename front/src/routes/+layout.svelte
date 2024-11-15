<script lang="ts">
	import '../tailwind.css';
</script>

<div class="flex flex-col min-h-screen">
	<!-- 네비게이션 바 -->
	<header class="bg-blue-500 text-white p-4 fixed top-0 left-0 w-full z-10">
	  <nav class="flex items-center justify-between px-4 py-5 shadow-md">
		<div class="flex items-center">
		  <a href="/home" class="text-3xl">GINC</a>
		</div>
	  </nav>
	</header>
  
	<!-- 메인 컨텐츠 -->
	<main class="flex-1 mt-[80px] mb-[60px]">
	  <!-- mt-[80px]: 네비게이션 바 높이만큼 마진 추가 -->
	  <!-- mb-[60px]: 푸터 높이만큼 마진 추가 -->

  	  <slot></slot> <!-- children() 대신 slot을 사용 -->
	</main>
  
	<!-- 푸터 -->
	<footer class="bg-gray-800 text-white p-4 fixed bottom-0 left-0 w-full">
	  <p class="text-center">
		&copy; {new Date().getFullYear()} My Application. All rights reserved.
	  </p>
	</footer>
  </div>
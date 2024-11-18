<script lang="ts">
	import '../tailwind.css';
</script>



<div class="flex flex-col min-h-screen">
	<!-- 네비게이션 바 -->
	<nav class="flex items-center justify-between flex-wrap bg-teal-500 p-6">
		<div class="flex items-center flex-shrink-0 text-white mr-6">
			<img src="https://i.postimg.cc/BvXN2Jg1/12345-removebg-preview.png" alt="GINC" class="h-8 w-8 mr-2" />
		  <span class="font-semibold text-xl tracking-tight">GINC</span>
		</div>
		<div class="block fixed right-5">
		  <button class="flex items-center px-3 py-2 border rounded text-teal-200 border-teal-400 hover:text-white hover:border-white">
			<svg class="fill-current h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Menu</title><path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/></svg>
		  </button>
		</div>
	  </nav>

	
  
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
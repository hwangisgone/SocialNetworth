<script>
	import "../app.css";
	import { Toaster } from 'svelte-french-toast';

	import Detached from './components/Detached.svelte';

	import PurrPostLogo from '$lib/purrpost-logo.svg?component'; 

	import SearchBar from './SearchBar.svelte';
	import PostPopup from './PostPopup.svelte';
	import NavigationBar from './NavigationBar.svelte';
	import RecommendationComp from './RecommendationComp.svelte';
</script>

<Toaster />

<Detached>
	<div class="p-4 flex flex-col gap-2 bg-black rounded-xl ">
		<span class="font-bold text-center">Temp test</span>
		<a href="./login" class="btn btn-primary btn-sm">Go to Login</a>
		<a href="./register" class="btn btn-primary btn-sm">Go to Register</a>
		<a href="./" class="btn btn-primary btn-sm">Go to Home</a>
	</div>
</Detached>



<div class="flex min-h-full w-full items-center justify-center text-2lx bg-white dark:bg-gray-800">
	<div class="nd-background w-screen min-h-1/2 border border-gray-900">
		<!-- component -->
	<div class="h-screen w-full relative flex overflow-hidden">

			<!-- Sidebar -->
			<aside class="h-full w-72 xs:w-96 xl:w-5/12 flex flex-col border-r border-gray-200 dark:border-gray-700 space-y-2 items-center relative text-white">
				<!-- Logo -->
				<div class="relative h-12 flex-shrink-0 w-10/12 text-gray-400 focus-within:text-light focus-within:text-gray-700">
					<a class="btn btn-ghost btn-circle" href="./"> 
						<PurrPostLogo width=30 height=30/>
					</a>
				</div>

				<NavigationBar />
<!-- 
				<PostPopup /> -->
			</aside>

			
		 
			<div class="w-full h-full flex flex-col justify-between">
				<!-- Header -->
				<header class="h-12 w-full flex items-center relative justify-end text-base">
				<!-- Profile -->
					<div class="flex w-full justify-between items-center border-b px-4 py-3 sticky top-0 border-l border-r border-gray-200 dark:border-gray-700">
					  <!-- Title -->
					  <h2 class="text-gray-800 dark:text-gray-100 font-bold font-sm">
						Home
					  </h2>
					  <!-- /Title -->
		  
					  <!-- Custom Timeline -->
					  <div>
						<svg onclick="document.querySelector('html').classList.toggle('dark')" viewBox="0 0 24 24" class="w-5 h-5 text-blue-400" fill="currentColor">
						  <g>
							<path d="M22.772 10.506l-5.618-2.192-2.16-6.5c-.102-.307-.39-.514-.712-.514s-.61.207-.712.513l-2.16 6.5-5.62 2.192c-.287.112-.477.39-.477.7s.19.585.478.698l5.62 2.192 2.16 6.5c.102.306.39.513.712.513s.61-.207.712-.513l2.16-6.5 5.62-2.192c.287-.112.477-.39.477-.7s-.19-.585-.478-.697zm-6.49 2.32c-.208.08-.37.25-.44.46l-1.56 4.695-1.56-4.693c-.07-.21-.23-.38-.438-.462l-4.155-1.62 4.154-1.622c.208-.08.37-.25.44-.462l1.56-4.693 1.56 4.694c.07.212.23.382.438.463l4.155 1.62-4.155 1.622zM6.663 3.812h-1.88V2.05c0-.414-.337-.75-.75-.75s-.75.336-.75.75v1.762H1.5c-.414 0-.75.336-.75.75s.336.75.75.75h1.782v1.762c0 .414.336.75.75.75s.75-.336.75-.75V5.312h1.88c.415 0 .75-.336.75-.75s-.335-.75-.75-.75zm2.535 15.622h-1.1v-1.016c0-.414-.335-.75-.75-.75s-.75.336-.75.75v1.016H5.57c-.414 0-.75.336-.75.75s.336.75.75.75H6.6v1.016c0 .414.335.75.75.75s.75-.336.75-.75v-1.016h1.098c.414 0 .75-.336.75-.75s-.336-.75-.75-.75z"></path>
						  </g>
						</svg>
					  </div>
					  <!-- /Custom Timeline -->
					</div>   
				</header>
<slot />


			 </div>

			<!-- Search Bar -->
			<aside class="h-full hidden xl:block xl:w-5/12 flex flex-col border-l border-gray-200 dark:border-gray-700 space-y-10 items-center relative">
				<SearchBar />

				<RecommendationComp />
			</aside>
		</div>

	</div>

</div>
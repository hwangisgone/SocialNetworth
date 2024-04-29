<script lang='ts'>
	import EyeIcon		from '$lib/icon/eye.svg?component';
	import HeartIcon	from '$lib/icon/heart.svg?component';
	import ReplyIcon	from '$lib/icon/talk-bubbles.svg?component';
	import ShareIcon	from '$lib/icon/share.svg?component';

	import PurrPostLogo from '$lib/purrpost-logo.svg?component'; 

	const postArray = [
		{
			pics: ['https://i.imgur.com/fjXFX93.jpeg'],
			viewCount: 10,
			likeCount: 3,
			replyCount: 1,
			shareCount: 0,
		},
		{
			pics: ['https://i.imgur.com/5yeBVeM.jpeg'],
			viewCount: 1,
			likeCount: 1,
			replyCount: 0,
			shareCount: 0,
		},
		{
			pics: ['https://i.imgur.com/DBpznrn.jpeg'],
			viewCount: 100,
			likeCount: 27,
			replyCount: 15,
			shareCount: 7,
		},
		{
			pics: ['https://i.imgur.com/ISpNf4j.jpeg'],
			viewCount: 20,
			likeCount: 20,
			replyCount: 19,
			shareCount: 0,
		}
	];

	import SearchBar from './SearchBar.svelte';
	import PostPopup from './PostPopup.svelte';
</script>

<div class="flex min-h-screen w-screen items-center justify-center rounded-3xl">
	<div class="nd-background w-screen min-h-1/2 border border-gray-900 rounded-3xl">
				<!-- component -->
		<div class="h-screen w-full relative flex overflow-hidden">

			<!-- Sidebar -->
			<aside class="h-full w-2/12 flex flex-col space-y-10 items-center relative bg-gray-800 text-white px-4">
				<!-- Logo -->
				<div class="relative flex-shrink-0 px-2 py-2 w-full text-gray-400 border-b dark:border-indigo-800 dark:focus-within:text-light focus-within:text-gray-700 text-center">
					<a class="btn btn-ghost btn-circle" href="./"> 
						<PurrPostLogo width=30 height=30/>
					</a>
				</div>

				<!-- Empty space -->
				<div class="pb-24"></div>

				<PostPopup />
			</aside>

			
		 
			<div class="w-7/12 h-full flex flex-col justify-between">
				<!-- Header -->
				<header class="h-16 w-full flex items-center relative justify-end px-5 space-x-10 bg-gray-800">
				<!-- Profile -->
					<div class="relative flex-shrink-0 px-2 py-2 w-full text-gray-400 dark:focus-within:text-light focus-within:text-gray-700">
							<button class="w-full p-2 bg-gray-50 rounded-full bg-opacity-0 font-bold text-white"> <a class="border-b dark:border-indigo-800">For you</a></button>
					</div>
				</header>

				<!-- Main -->
				<main class="max-w-full h-full flex relative overflow-y-hidden">
					<!-- Container -->
					<div id="imageContainer" class="h-full w-full flex flex-wrap items-start bg-opacity-0 justify-start rounded-tl grid-flow-col auto-cols-max gap-4 overflow-y-scroll">
						<!-- Container -->
						<!-- For each in svelte -->
						{#each postArray as post}
							<div class="w-full bg-opacity-0 p-10">
								<img class="w-full object-cover" src={post.pics[0]}>
								<ul class="mt-3 flex flex-wrap">
									<li class="mr-auto">
										<a href="#" class="flex text-gray-400 hover:text-gray-600">
											<ShareIcon width=24 height=24/>
											{post.shareCount}
										</a>
									</li>
									<li class="mr-2">
										<a href="#" class="flex text-gray-400 hover:text-gray-600">
											<EyeIcon width=24 height=24/>
											{post.viewCount}
										</a>
									</li>
									<li class="mr-2">
										<a href="#" class="flex text-gray-400 hover:text-gray-600">
											<ReplyIcon width=24 height=24/>
											{post.replyCount}
										</a>
									</li>
									<li>
										<a href="#" class="flex text-gray-400 hover:text-gray-600">
											<HeartIcon width=24 height=24/>
											{post.likeCount}
										</a>
									</li>
								</ul>
							</div>
						{/each}
						<!-- For each in svelte -->
					</div>
				</main>

			</div>

			<!-- Search Bar -->
			<aside class="h-full w-3/12 flex flex-col space-y-10 items-center relative bg-gray-800 text-white">
				<SearchBar />
			</aside>
		</div>

	</div>

</div>
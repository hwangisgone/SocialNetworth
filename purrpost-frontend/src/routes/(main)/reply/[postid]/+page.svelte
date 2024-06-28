<script lang='ts'>
	import ContentBody  from '../../components/post/ContentBody.svelte';
	import Post from '../../components/post/Post.svelte';

	import { page } from '$app/stores';
	import { getPost, getPostReplies } from '$lib/postapi';

// $page.params.postid

	let dataLoaded = false;
	let replyList = [];
	let currentPost = {};

async function loadAll() {
	replyList = await getPostReplies($page.params.postid);
	currentPost = await getPost($page.params.postid);

	console.log(currentPost);
	dataLoaded = true;
}

	loadAll();
</script>

<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->
	<div id="imageContainer" class="w-full m-4 flex flex-col  items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		<Post postInfo={currentPost}/>
		<div class="ml-4 pl-4 border-l-4 border-gray-200">
			{#if replyList.length > 0}
				<ContentBody postList={replyList}/>
			{:else}
				<span class="font-semibold">No reply...</span>
			{/if}
		</div>

	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>
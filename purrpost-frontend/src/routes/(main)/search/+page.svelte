<script lang='ts'>
	import ContentBody  from '../components/post/ContentBody.svelte';
	import SearchBar from '../SearchBar.svelte';
	import { searchPosts } from '$lib/postapi';

	import { page } from '$app/stores';

	const searchQuery = $page.url.searchParams.get('term');

	let postList = [];

let dataLoaded = false;

async function loadAll() {
	postList = await searchPosts(searchQuery);
	dataLoaded = true;
}

	loadAll();

</script>

<SearchBar term={searchQuery}/>
<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->
	<div id="imageContainer" class="w-full m-4 flex flex-col  items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		{#if postList.length > 0}
			<ContentBody {postList}/>
		{:else}
			<p class="font-semibold">Nothing found.</p>
		{/if}
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>
	  
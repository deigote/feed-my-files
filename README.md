# feed-my-files
A command line tool to build RSS2 and ATOM feeds from a given file system using the last modification time as publish date

## What

Feed My Files is a Java based command line tool to generate a feed from a file system, using the last modified date 
to sort the feed entries. It is written in `Groovy` and use `Gradle` to manage the dependencies and launch an 
execution.

## How

You can launch Feed My Files using a command like the following from the project's directory:

```
./gradlew buildFeeds \
  -ProotPath=/home/shared \
  -Prss2File=/var/www/rss2.xml \
  -PatomFile=/var/www/atom.xml \
  -PhtmlFile=/var/www/feed.html \
  -PurlPrefix=https://lab.com/newFiles \
  -PfilePrefixToIgnoreInUrl=/home/shared \
  -PfeedTitle="New Shared Files" \
  -PfeedDescription="New shared files in our lab" \
  -PfeedLink=https://lab.com/feed \
  -PfileExtensionsToInclude=pdf,mp3,png
```

The following options are **mandatory**:

 - `rootPath`: The path where the files to feed are located
 - any of `rss2File`, `atomFile` or `htmlFile`: output file for the different supported formats.
 
The following options are, well, **optional**:
 - `urlPrefix`: if passed, the file URLs will be built using it as a prefix (instead of them being `file://` URLs) 
 - `filePrefixToIgnoreInUrl`: if passed, it will be removed from the beginning of each file when building its URL. It can only be used if `urlPrefix` is present.
 - `feedTitle`: What to use as the title of the feed
 - `feedDescription`: What to use as the description of the feed 
 - `feedLink`: What to use as the link of the feed
 - `fileExtensionsToInclude`: A comma-separated list of the files extensions to include in the feed
 
### With docker
Launching Feed My Files with `docker` is easy. The following command is equivalent to the one posted before, but 
using `docker` instead:

```
docker run --rm \
	-v $HOME/.gradle:/root/.gradle \
	-v /var/www/:/out \
	-v /home/shared:/files \
	-v $(pwd):/app \
	java bash -c 'cd /app; ./gradlew buildFeeds -ProotPath=/files -Prss2File=/out/rss2.xml -PatomFile=/out/atom.xml -PhtmlFile=/out/feed.html -PurlPrefix=https://lab.com/newFiles -PfilePrefixToIgnoreInUrl=/files -PfeedTitle="New Shared Files" -PfeedDescription="New shared files in our lab" -PfeedLink=https://lab.com/feed -PfileExtensionsToInclude=pdf,mp3,png'
	
	

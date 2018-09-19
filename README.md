# MyownTest
项目自测文件

1.包括自定义dialog
2.nio
3.thread
4.proxy
5.list
7.exception
8.gradle

http://www.importnew.com/29782.html
物理内存：就是系统硬件提供的内存大小，是真正的内存，一般叫做内存条。
也叫随机存取存储器（random access memory，RAM）又称作“随机存储器”，是与CPU直接交换数据的内部存储器，
也叫主存(内存)。

虚拟内存：相对于物理内存，在Linux下还有一个虚拟内存的概念，
虚拟内存就是为了满足物理内存的不足而提出的策略，它是利用磁盘空间虚拟出的一块逻辑内存，
用作虚拟内存的磁盘空间被称为交换空间（Swap Space）。Linux会在物理内存不足时，使用虚拟内存，
内核会把暂时不用的内存块信息写到虚拟内存，这样物理内存就得到了释放，这块儿内存就可以用于其他目的，
而需要用到这些内容的时候，这些信息就会被重新从虚拟内存读入物理内存。

Linux的buffers与cached
在Linux中经常发现空闲的内存很少，似乎所有的内存都被消耗殆尽了，
表面上看是内存不够用了，很多新手看到内存被“消耗殆尽”非常紧张，
其实这个是因为Linux系统将空闲的内存用来做磁盘文件数据的缓存。
这个导致你的系统看起来处于内存非常紧急的状况。但是实际上不是这样。这个区别于Windows的内存管理。
Linux会利用空闲的内存来做cached & buffers。
buffers是指用来给块设备做的缓冲大小（块设备的读写缓冲区），它只记录文件系统的metadata以及 tracking in-flight pages.

cached是作为page cache的内存, 文件系统的cache。你读写文件的时候，
Linux内核为了提高读写性能与速度，会将文件在内存中进行缓存，这部分内存就是Cache Memory(缓存内存)。
即使你的程序运行结束后，Cache Memory也不会自动释放。这就会导致你在Linux系统中程序频繁读写文件后，
你会发现可用物理内存会很少。其实这缓存内存(Cache Memory)在你需要使用内存的时候会自动释放，
所以你不必担心没有内存可用。

共享内存是进程间通信中最简单的方式之一。共享内存允许两个或更多进程访问同一块内存，
就如同 malloc() 函数向不同进程返回了指向同一个物理内存区域的指针。当一个进程改变了这块地址中的内容的时候，其它进程都会察觉到这个。
其实所谓共享内存，就是多个进程间共同地使用同一段物理内存空间，它是通过将同一段物理内存映射到不同进程的虚拟空间来实现的。
由于映射到不同进程的虚拟空间中，不同进程可以直接使用，不需要像消息队列那样进行复制，所以共享内存的效率很高。
共享内存可以通过mmap()映射普通文件机制来实现，也可以System V共享内存机制来实现，System V是通过映射特殊文件系统shm中的文件实现进程间的共享内存通信，
也就是说每个共享内存区域对应特殊文件系统shm中的一个文件。

另外，我们还必须了解RSS、PSS、USS等相关概念：

VSS – Virtual Set Size 虚拟耗用内存（包含共享库占用的内存）
RSS – Resident Set Size 实际使用物理内存（包含共享库占用的内存）
PSS – Proportional Set Size 实际使用的物理内存（比例分配共享库占用的内存）
USS – Unique Set Size 进程独自占用的物理内存（不包含共享库占用的内存）
RSS（Resident set size），使用top命令可以查询到，是最常用的内存指标，表示进程占用的物理内存大小。
但是，将各进程的RSS值相加，通常会超出整个系统的内存消耗，这是因为RSS中包含了各进程间共享的内存。

PSS（Proportional set size）所有使用某共享库的程序均分该共享库占用的内存时，每个进程占用的内存。
显然所有进程的PSS之和就是系统的内存使用量。它会更准确一些，它将共享内存的大小进行平均后，再分摊到各进程上去。
USS(Unique set size )进程独自占用的内存，它是PSS中自己的部分，它只计算了进程独自占用的内存大小，不包含任何共享的部分。

top命令查看
执行top命令后，执行SHIFT +F ，可以选择按某列排序，例如选择n后，就会按字段%MEM排序
当然也可以使用shift+m 或大写键M 让top命令按字段%MEM来排序，当然你也可以按VIRT（虚拟内存）、
SWAP（进程使用的SWAP空间）、RES(实际使用物理内存,当然这里由于涉及共享内存缘故，你看到的实际内存非常大)

%MEM — Memory usage (RES)

A task’s currently used share of available physical memory

VIRT — virtual memory

The total amount of virtual memory used by the task. It includes all code, data and shared libraries plus pages that have been swapped out. (Note: you can define the STATSIZE=1 environment variable and the VIRT will be calculated from the /proc/#/state VmSize field.)

VIRT = SWAP + RES

SWAP — Swapped size (kb)

The swapped out portion of a task’s total virtual memory image.

RES — Resident size (kb)

RES = CODE + DATA.

是否有人会觉得奇怪，为什么%MEM这一列的值加起来会大于100呢？
这个是因为这里计算的时候包含了共享内存的缘故，另外由于共享内存的缘故，
你看到进程使用VIRT或RES都非常高。由于大部分的物理内存通常在多个应用程序之间共享，
名为实际使用物理内存（RSS，对应top命令里面的RES）的这个标准的内存耗用衡量指标会大大高估内存耗用情况。

ps命令查看
使用ps命令找出占用内存资源最多的20个进程（数量可以任意设置）

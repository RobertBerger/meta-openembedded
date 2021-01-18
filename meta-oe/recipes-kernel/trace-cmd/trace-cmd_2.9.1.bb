SUMMARY = "User-space front-end command-line tool for ftrace"

LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=873f48a813bded3de6ebc54e6880c4ac"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/rostedt/trace-cmd.git;branch=master \
	file://0001-trace-cmd-make-it-build-with-musl.patch"

SRCREV = "530b1a0caef39466e16bbd49de5afef89656f03f"

S = "${WORKDIR}/git"

do_install() {
       oe_runmake etcdir=${sysconfdir} DESTDIR=${D} install
}

# ${libdir}/traceevent/plugins clashes with package perf
# except for plugin_blk.so it seems
# so we put it in it's own package

# FILES_${PN}-plugins = "${libdir}/traceevent/plugins"

FILES_${PN} += "${libdir}/traceevent/plugins/plugin_blk.so"

PACKAGES =+ "${PN}-trcevplug"
FILES_${PN}-trcevplug += " \
     ${libdir}/traceevent/plugins \
"

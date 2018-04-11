SUMMARY = "This is a filesystem client based on the SSH File Transfer Protocol using FUSE"
AUTHOR = "Nikolaus Rath <Nikolaus@rath.org>"
HOMEPAGE = "http://fuse.sourceforge.net/sshfs.html"
SECTION = "console/network"
LICENSE = "GPLv2"
DEPENDS = "glib-2.0 fuse3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/libfuse/sshfs"
SRCREV = "7f56cfc60233863eff1b580d56aacc87d4e2e2ba"

S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES_${PN} += "${libdir}/sshnodelay.so"

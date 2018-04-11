SUMMARY = "This is a filesystem client based on the SSH File Transfer Protocol using FUSE"
AUTHOR = "Nikolaus Rath <Nikolaus@rath.org>"
HOMEPAGE = "http://fuse.sourceforge.net/sshfs.html"
SECTION = "console/network"
LICENSE = "GPLv2"
DEPENDS = "glib-2.0 fuse3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/libfuse/sshfs"
SRCREV = "30a2668b99ed2f513e008a47e9ea09549f6afe2e"

S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES_${PN} += "${libdir}/sshnodelay.so"
